import unittest
from unittest.mock import patch
from app.models.User import User  # User 클래스가 있는 경로에 맞게 수정하세요.


class TestUser(unittest.TestCase):

    @patch('logging.Logger.info')  # logging 메시지를 모킹
    def test_valid_student_number(self, mock_log_info):
        # 올바른 학번으로 User 인스턴스 생성
        user = User("123456", "Alice", "mem123", "password", "student", "avatar.png", "01012345678", "2021040035",
                    "2000-01-01", "alice@example.com")

        # 로그 메시지 확인
        mock_log_info.assert_called_with("student_number=%s", "2021040035")
        self.assertEqual(user.student_number, "2021040035")  # 정상적으로 student_number가 설정되었는지 확인

    @patch('logging.Logger.info')  # logging 메시지를 모킹
    def test_invalid_student_number(self, mock_log_info):
        # 잘못된 학번을 입력하여 User 인스턴스 생성 시 ValueError가 발생하는지 확인
        with self.assertRaises(ValueError) as context:
            User("123456", "Alice", "mem123", "password", "student", "avatar.png", "01012345678", "123", "2000-01-01",
                 "alice@example.com")

        # ValueError 메시지가 올바른지 확인
        self.assertEqual(str(context.exception), "student_number이 regex를 만족하지 않음.")


if __name__ == '__main__':
    unittest.main()
