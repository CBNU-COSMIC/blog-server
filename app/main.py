from fastapi import FastAPI, Form

app = FastAPI()

@app.get("/")
async def root():
    return {"message": "Hello World"}

