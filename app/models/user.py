import datetime
import logging
import re


class User:
    student_number_regex = re.compile(r"^\d{6,11}$")
    student_password_regex_upper=re.compile(r'[A-Z]')
    student_password_regex_lower=re.compile(r'[a-z]')
    student_password_regex_digits=re.compile(r'/d')
    student_password_regex_special_word=re.compile(r'[!@#$%^&*()_+~`{}\[\]:;"\'<>,.?/\\|-]')

    def __init__(self, id: str, name: str, member_id: str, password: str, role: str, avatar: str, phone_number: str,
                 student_number: str, birth: datetime, email: str):
        self._validate_password(password=password)

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

    def _validate_student_number(self, student_number: str) -> None:
        """
        학번의 유효성을 검사합니다.
        """
        if student_number is None:
            raise ValueError(f"현재 학번: None")

        if User.student_number_regex.match(student_number) is None:
            raise ValueError(f"현재 학번: {student_number}")

    def _validate_password(self, password):
        """
        비밀번호의 유효성을 검사합니다.
        """
        count = 0
        satisfying_condition=3
        if password is None:
            raise ValueError(f"현재 비밀번호: None")

        if User.student_password_regex_lower.search(password):
            count += 1
        if User.student_password_regex_upper.search(password):
            count += 1
        if User.student_password_regex_digits.search(password):
            count += 1
        if User.student_password_regex_special_word.search(password):
            count += 1

        if len(password) < 8 or len(password) > 12:
            raise ValueError("password가 regex를 만족하지 않음.")
        if count < satisfying_condition:
            raise ValueError("password가 regex를 만족하지 않음.")

