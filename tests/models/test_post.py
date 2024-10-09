from unittest import TestCase

from app.models.post import Post
from app.models.user import User
from datetime import datetime


class TestPost(TestCase):
    def test_valid_post_creation(self):
        # Given
        post_id = "1"
        title = "title"
        attribute = "attribute"
        content = "content"
        user_id = "1"
        board = "board"
        created_at = datetime.now()

        # When
        post = Post(id=post_id, title=title, attribute=attribute, content=content, user_id=user_id, board=board,
                    created_at=created_at)

        # Then
        self.assertEqual(post.id, post_id)
        self.assertEqual(post.title, title)
        self.assertEqual(post.attribute, attribute)
        self.assertEqual(post.content, content)
        self.assertEqual(post.user_id, user_id)
        self.assertEqual(post.board, board)
        self.assertEqual(post.created_at, created_at)

    def test_post_repr(self):
        # Given
        user_id = "test"
        name = "test"
        member_id = "test"
        password = "test1234!"
        role = "test"
        avatar = "test"
        phone_number = "01012345678"
        student_number = "202000000"
        birth = datetime.now()
        email = "test@test.com"
        writer = User(id=user_id, name=name, member_id=member_id, password=password, role=role, avatar=avatar,
                      phone_number=phone_number, student_number=student_number, birth=birth, email=email)

        post_id = "1"
        title = "title"
        attribute = "attribute"
        content = "content"
        user_id = "1"
        board = "board"
        created_at = datetime.now()

        # When
        post = Post(id=post_id, title=title, attribute=attribute, content=content, user_id=user_id, board=board,
                    created_at=created_at)

        # Then
        self.assertEqual(repr(post),
                         f"Post(id={post_id!r}, title={title!r}, attribute={attribute!r}, content={content!r}, "
                         f"writer={writer!r}, board={board!r}, created_at={created_at!r})")
