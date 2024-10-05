import datetime
import unittest
from unittest.mock import patch
from app.models.user import User  # User 클래스가 있는 경로에 맞게 수정하세요.


class TestUser(unittest.TestCase):

    def test_valid_user_creation(self):
        # Given
        id = "test"
        name = "test"
        member_id = "test"
        password = "test"
        role = "test"
        avatar = "test"
        phone_number = "01012345678"
        student_number = "202000000"
        birth = datetime.datetime.now()
        email = "test@test.com"

        # When
        user = User(id=id, name=name, member_id=member_id, password=password, role=role, avatar=avatar,
                    phone_number=phone_number, student_number=student_number, birth=birth, email=email)

        # Then
        self.assertEqual(user.id, id)
        self.assertEqual(user.name, name)
        self.assertEqual(user.member_id, member_id)
        self.assertEqual(user.password, password)
        self.assertEqual(user.role, role)
        self.assertEqual(user.avatar, avatar)
        self.assertEqual(user.phone_number, phone_number)
        self.assertEqual(user.student_number, student_number)
        self.assertEqual(user.birth, birth)
        self.assertEqual(user.email, email)

    def test_invalid_user_creation(self):
        # Given
        test_cases = [
            ("test", "test", "test", "test", "test", "test", "01012345678", None, datetime.datetime.now(),
             "test@test.com"),
            ("test", "test", "test", "test", "test", "test", "01012345678", "invalid_number", datetime.datetime.now(),
             "test@test.com")
        ]

        # Expect
        for test_case in test_cases:
            with self.assertRaises(ValueError):
                User(*test_case)


