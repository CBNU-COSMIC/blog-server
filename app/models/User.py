import logging
import re
logging.basicConfig(level=logging.INFO)#전역적으로 로깅 수준 설정
log = logging.getLogger("user")#User class 전용 log

class User:
    def __init__(self,id,name,member_id,password,role,avartar,phone_number,student_number,birth,email):
        self._validate_student_num(id)
        self.log=log

        self.id = id
        self.name = name
        self.member_id = member_id
        self.password = password
        self.role = role
        self.avartar = avartar
        self.phone_number = phone_number
        self.student_number = student_number
        self.birth = birth
        self.email = email

        #re.match: 문자열과 패턴을 입력하면 그때마다 입력된 문자열과 패턴이 일치하는지 비교
        #re.compile: 패턴을 객체형태로 저장해 놓고 문자열만 넣어주면 패턴에 맞는지 비교 ->여러번 사용 시 성능 최적화하기 좋음.
        self.student_number_regex= re.compile("^\d{6,11}$")# 학번이 valid한지 확인하는 상황이 더 나올 수 있을듯 해서 re.compile()을 이용해 필드로 만들었습니다.

    def _validate_student_num(self, student_num):
        self.log.info("/validId 컨트롤러로 들어온 Id=%s", student_num)
        if self.student_number_regex.match(student_num) is None:
            self.log.info("/validId 컨트롤러로 들어온 Id가 regex를 만족하지 않음.")
            raise ValueError("/validId 컨트롤러로 들어온 Id가 regex를 만족하지 않음.")

