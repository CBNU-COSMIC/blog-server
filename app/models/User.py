import logging
import re

logging.basicConfig(level=logging.INFO)
log = logging.getLogger("user")

class User:
    def __init__(self,id,name,memberId,password,role,avartar,phoneNumber,studentNumber,birth,email):
        self.log=log
        self._validate_pw(password)

        self.id = id
        self.name = name
        self.memberId = memberId
        self.password = password
        self.role = role
        self.avartar = avartar
        self.phoneNumber = phoneNumber
        self.studentNumber = studentNumber
        self.birth = birth
        self.email = email


    def _validate_pw(self, password):
        count=0
        #주어진 조건중에 3개 이상 만족 할 시 유효
        if re.search(r'[A-Z]', password) is not None:
            log.info("Password must contain at least one uppercase letter")
            count+=1
        if re.search(r'[a-z]', password) is not None:
            log.info("Password must contain at least one lowercase letter")
            count+=1
        if re.search(r'\d', password) is not None:
            log.info("Password must contain at least one number")
            count+=1
        if re.search(r'[!@#$%^&*()_+~`{}\[\]:;"\'<>,.?/\\|-]', password) is not None:
            log.info("Password must contain at least one special character")
            count+=1

        if count<3:
            log.info("password가 regex를 만족하지 않음.")
            raise ValueError("password가 regex를 만족하지 않음.")
        elif len(password) < 8 or len(password)>12:
            log.info("password가 regex를 만족하지 않음.")
            raise ValueError("password가 regex를 만족하지 않음.")
