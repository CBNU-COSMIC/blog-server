import datetime
import re


class Comment:
    content_regex_has_letter = re.compile('.*[a-zA-Z가-힣]+.*')
    content_regex_not_script_or_tag = re.compile('<\s*/?\s*\w+\s*[^>]*>')
    def __init__(self, id: str, user_id: str, content: str, parent_id: str, comment_date: datetime):
        self._validate_content(content = content)
        self.__id = id
        self.__user_id = user_id
        self.__content = content
        self.__parent_id = parent_id
        self.__comment_date = comment_date

    @property
    def id(self) -> str:
        return self.__id

    @property
    def user_id(self) -> str:
        return self.__user_id

    @property
    def content(self) -> str:
        return self.__content

    @property
    def parent_id(self) -> str:
        return self.__parent_id

    @property
    def comment_date(self) -> str:
        return self.__comment_date

    def __repr__(self):
        return (f"Comment(id={self.__id!r}, user_id={self.__user_id!r}, content={self.__content!r},"
                f" parent_id={self.__parent_id!r}, comment_date={self.__comment_date!r})")

    def _validate_content(self, content) -> None:
        """
        댓글의 유효성을 검사합니다.
        """
        if Comment.content_regex_has_letter.match(content) is None:
            raise ValueError(f"현재 댓글: {content}")

        if Comment.content_regex_not_script_or_tag.search(content) is not None:
            raise ValueError(f"현재 댓글: {content}")





