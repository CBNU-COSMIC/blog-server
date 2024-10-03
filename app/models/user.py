from datetime import datetime
import re


class User:
    student_number_regex = re.compile(r"^\d{6,11}$")

    def __init__(self, id: str, name: str, member_id: str, password: str, role: str, avatar: str, phone_number: str,
                 student_number: str, birth: datetime, email: str):
        self._validate_student_number(student_number=student_number)

        self.__id = id
        self.__name = name
        self.__member_id = member_id
        self.__password = password
        self.__role = role
        self.__avatar = avatar
        self.__phone_number = phone_number
        self.__student_number = student_number
        self.__birth = birth
        self.__email = email

    @property
    def id(self) -> str:
        return self.__id

    @property
    def name(self) -> str:
        return self.__name

    @property
    def member_id(self) -> str:
        return self.__member_id

    @property
    def password(self) -> str:
        return self.__password

    @property
    def role(self) -> str:
        return self.__role

        #re.match: 문자열과 패턴을 입력하면 그때마다 입력된 문자열과 패턴이 일치하는지 비교
        #re.compile: 패턴을 객체형태로 저장해 놓고 문자열만 넣어주면 패턴에 맞는지 비교 ->여러번 사용 시 성능 최적화하기 좋음.
    @property
    def avatar(self) -> str:
        return self.__avatar

    @property
    def phone_number(self) -> str:
        return self.__phone_number

    @property
    def student_number(self) -> str:
        return self.__student_number

    @property
    def birth(self) -> datetime:
        return self.__birth

    @property
    def email(self) -> str:
        return self.__email

