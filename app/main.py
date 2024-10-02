import logging
import re
from fastapi import FastAPI, Form

app = FastAPI()

@app.get("/")
async def root():
    return {"message": "Hello World"}

#request: post / form 방식
#response: {"isValid": False | True }
@app.post("/validId")
async def validId(id:int = Form(...)):
    logging.basicConfig(level=logging.INFO)
    validPattern = r'^\d{6,11}$'
    log = logging.getLogger("myapp")
    log.info("/validId 컨트롤러로 들어온 Id=%s", id)
    if re.match(validPattern,str(id)) is None:
        log.info("/validId 컨트롤러로 들어온 Id가 regex를 만족하지 않음.")
        return {"isValid":False}
    else:
        log.info("/validId 컨트롤러로 들어온 Id가 regex를 만족함.")
        return {"isValid":True}



