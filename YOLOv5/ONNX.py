import onnxruntime as ort
import numpy as np

# ONNX 파일 로드
ort_session = ort.InferenceSession("yolov5.onnx")

# 입력 데이터 준비 (예시로 랜덤 데이터 생성)
dummy_input = np.random.randn(1, 3, 640, 640).astype(np.float32)

# ONNXRuntime 세션을 사용하여 추론 실행
outputs = ort_session.run(None, {"onnx::Cast_0": dummy_input})

# 결과 출력
print(outputs)
