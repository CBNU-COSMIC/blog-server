import logging
import re

class User:
    def __init__(self,id,name,memberId,password,role,avartar,phoneNumber,studentNumber,birth,email):
        self._validate_std_num(id)

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

    def _validate_std_num(self, student_num):
        logging.basicConfig(level=logging.INFO)
        validPattern = r'^\d{6,11}$'
        log = logging.getLogger("myapp")
        log.info("/validId 컨트롤러로 들어온 Id=%s", student_num)
        if re.match(validPattern, str(student_num)) is None:
            log.info("/validId 컨트롤러로 들어온 Id가 regex를 만족하지 않음.")
            raise ValueError("/validId 컨트롤러로 들어온 Id가 regex를 만족하지 않음.")

    def _validate_pw(self,)