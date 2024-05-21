import torch

print(torch.cuda.is_available())  # CUDA 사용 가능 여부 확인
print(torch.cuda.device_count())  # 사용 가능한 CUDA 디바이스 수 확인

print(torch.__version__)

import tensorflow as tf

print(tf.__version__)