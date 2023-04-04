FROM tensorflow/tensorflow:2.9.3-gpu

WORKDIR /usr/src/app

# install requirements.txt
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

ENTRYPOINT ["python3", "app.py"]
