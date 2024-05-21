package com.example.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ImageProcessingController {
    private static final String PYTHON_SCRIPT_PATH = "C:\\Users\\82103\\Desktop\\project\\back-end\\YOLOv5\\yolov5\\evalImage.py";
    private static final String PYTHON_INTERPRETER_PATH = "D:\\Anaconda\\envs\\my_env\\python.exe";

    @GetMapping(value = "/testPythonScript")
    public CompletableFuture<ResponseEntity<String>> testPythonScript() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ProcessBuilder pb = new ProcessBuilder(PYTHON_INTERPRETER_PATH, PYTHON_SCRIPT_PATH);
                Process process = pb.start();

                StringBuilder output = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    log.info(output.toString());
                    return ResponseEntity.ok(output.toString());
                } else {
                    String errorOutput = new BufferedReader(new InputStreamReader(process.getErrorStream()))
                            .lines().collect(Collectors.joining("\n"));
                    log.error("파이썬 에러 출력 : " + errorOutput);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error executing python script");
                }
            } catch (IOException | InterruptedException e) {
                log.error("스크립트 실행 중 에러 발생", e);
                Thread.currentThread().interrupt();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error executing python script");
            }
        });
    }
}
