from datetime import datetime
from unittest import TestCase

from app.models.comment import Comment


class TestPost(TestCase):
    def test_valid_comment_creation(self):
        # Given
        id = "1"
        user_id = "2020000000"
        content = "content"
        parent_id = "0"
        comment_date = datetime.now()

        # When
        comment = Comment(id=id, user_id=user_id, content=content, parent_id=parent_id, comment_date=comment_date)

        # Then
        self.assertEqual(comment.id, id)
        self.assertEqual(comment.user_id, user_id)
        self.assertEqual(comment.content, content)
        self.assertEqual(comment.parent_id, parent_id)
        self.assertEqual(comment.comment_date, comment_date)

    def test_comment_repr(self):
        # Given
        id = "1"
        user_id = "2020000000"
        content = "content"
        parent_id = "0"
        comment_date = datetime.now()

        # When
        comment = Comment(id=id, user_id=user_id, content=content, parent_id=parent_id, comment_date=comment_date)

        # Then
        self.assertEqual(repr(comment),
                         f"Comment(id={id!r}, user_id={user_id!r}, content={content!r}, "
                         f"parent_id={parent_id!r}, comment_date={comment_date!r})")
