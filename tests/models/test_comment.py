from datetime import datetime
from unittest import TestCase

from app.models.comment import Comment


class TestPost(TestCase):
    def test_comment_creation(self):
        # Given
        id="154"
        user_id="2021040035"
        content="content"
        parent_id="2021040035"
        comment_date=datetime.now()

        # When
        comment = Comment(id=id, user_id=user_id, content=content, parent_id=parent_id,comment_date=comment_date)

        # Then
        self.assertEqual(comment.id, id)
        self.assertEqual(comment.user_id, user_id)
        self.assertEqual(comment.content, content)
        self.assertEqual(comment.parent_id, parent_id)
        self.assertEqual(comment.comment_date, comment_date)

