package com.example.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ImageProcessingController {
    private static final String PYTHON_SCRIPT_PATH = "C:\\Users\\82103\\Desktop\\project\\back-end\\YOLOv5\\yolov5\\evalImage.py";
    private static final String PYTHON_INTERPRETER_PATH = "D:\\Anaconda\\envs\\my_env\\python.exe";

    @Tag(name = "ImageProcessingController : 이미지 분석", description = "ImageProcessing Controller")
    @Operation(summary = "이미지 분석", description = "사용자가 전송한 이미지를 분석하는 API")
    @PostMapping(value = "/detectedImage")
    public CompletableFuture<ResponseEntity<String>> PythonScript(@RequestParam("file") MultipartFile file) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 업로드된 파일을 임시 파일로 저장
                File tempFile = File.createTempFile("uploaded-", ".jpg");
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(file.getBytes());
                }

                log.info("Temp file created at: " + tempFile.getAbsolutePath());

                // Python 스크립트에 이미지 파일 경로를 인수로 전달
                ProcessBuilder pb = new ProcessBuilder(PYTHON_INTERPRETER_PATH, PYTHON_SCRIPT_PATH, tempFile.getAbsolutePath());
                Process process = pb.start();

                StringBuilder output = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    log.info("Python script executed successfully.");
                    log.info("Python script output: " + output.toString());

                    // JSON 응답 파싱
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonOutput = output.toString();
                    Object json = objectMapper.readValue(jsonOutput, Object.class);
                    String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

                    return ResponseEntity.ok(jsonResponse);
                } else {
                    String errorOutput = new BufferedReader(new InputStreamReader(process.getErrorStream()))
                            .lines().collect(Collectors.joining("\n"));
                    log.error("Python script error output: " + errorOutput);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error executing python script");
                }
            } catch (IOException | InterruptedException e) {
                log.error("Script execution error", e);
                Thread.currentThread().interrupt();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error executing python script");
            }
        }).orTimeout(120, TimeUnit.SECONDS).exceptionally(ex -> {
            if (ex instanceof TimeoutException) {
                log.error("Python script execution timed out", ex);
                return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Python script execution timed out");
            } else {
                log.error("Unexpected error occurred", ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
            }
        });
    }

    @Tag(name = "ImageProcessingController : 이미지 분석", description = "ImageProcessing Controller")
    @Operation(summary = "이미지 전송", description = "분석된 결과로 사물 추천하는 API")
    @GetMapping(value = "/furniture/{furniture}/{mood}/{num}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String furniture, @PathVariable String mood, @PathVariable String num)throws IOException{
        log.info("IMG 요청들어옴");
        InputStream is = new FileInputStream("C:/Temp/interior furniture/"  + furniture + "/" + mood + "_" + num +  ".jpg" );
        return IOUtils.toByteArray(is);
    }
}
