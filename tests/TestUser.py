import unittest
from unittest.mock import patch
from app.models.User import User  # User 클래스가 있는 경로에 맞게 수정하세요.


class TestUser(unittest.TestCase):

    @patch('logging.Logger.info')  # logging 메시지를 모킹
    def test_valid_password(self, mock_log_info):
        # 유효한 비밀번호를 가진 유저 생성
        user = User("123456", "Alice", "mem123", "Valid123@", "student", "avatar.png", "01012345678", "123456",
                    "2000-01-01", "alice@example.com")

        # 비밀번호가 유효하므로 예외가 발생하지 않음
        self.assertEqual(user.password, "Valid123@")


    @patch('logging.Logger.info')  # logging 메시지를 모킹
    def test_invalid_password_length_and_complexity(self, mock_log_info):
        # 짧고 복잡성이 부족한 비밀번호로 유저 생성 시 ValueError가 발생하는지 확인
        with self.assertRaises(ValueError) as context:
            User("123456", "Alice", "mem123", "abc", "student", "avatar.png", "01012345678", "123456", "2000-01-01",
                 "alice@example.com")

        # ValueError의 메시지가 정확한지 확인
        self.assertEqual(str(context.exception), "password가 regex를 만족하지 않음.")

        # 로그 메시지 확인
        mock_log_info.assert_called_with("password가 regex를 만족하지 않음.")

    @patch('logging.Logger.info')  # logging 메시지를 모킹
    def test_invalid_password_no_special_char(self, mock_log_info):
        # 특수 문자랑 대문자가 없는 비밀번호로 테스트
        with self.assertRaises(ValueError) as context:
            User("123456", "Alice", "mem123", "password123", "student", "avatar.png", "01012345678", "123456",
                 "2000-01-01", "alice@example.com")

        # ValueError의 메시지가 정확한지 확인
        self.assertEqual(str(context.exception), "password가 regex를 만족하지 않음.")

        # 로그 메시지 확인
        mock_log_info.assert_called_with("password가 regex를 만족하지 않음.")

    @patch('logging.Logger.info')  # logging 메시지를 모킹
    def test_invalid_password_no_uppercase(self, mock_log_info):
        # 대문자만 없는 비밀번호로 테스트
        user = User("123456", "Alice", "mem123", "password123@", "student", "avatar.png", "01012345678", "123456",
                    "2000-01-01", "alice@example.com")

        # ValueError의 메시지가 정확한지 확인
        self.assertEqual(user.password, "password123@")


if __name__ == '__main__':
    unittest.main()
