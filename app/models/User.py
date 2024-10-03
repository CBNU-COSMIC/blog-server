import logging
import re

logging.basicConfig(level=logging.INFO)
log = logging.getLogger("user")

class User:
    def __init__(self,id,name,memberId,password,role,avartar,phoneNumber,studentNumber,birth,email):
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
        has_upper = re.search(r'[A-Z]', password) is not None
        has_lower = re.search(r'[a-z]', password) is not None
        has_digit = re.search(r'\d', password) is not None
        has_special = re.search(r'[!@#$%^&*()_+~`{}\[\]:;"\'<>,.?/\\|-]', password) is not None
        categories_met = sum([has_upper, has_lower, has_digit, has_special])
        if len(password) < 8 and categories_met < 3:
            log.info("/validPw 컨트롤러로 들어온 password가 regex를 만족하지 않음.")
            raise ValueError("/validPw 컨트롤러로 들어온 password가 regex를 만족하지 않음.")