import logging
import re

class User:
    def __init__(self,id,username,role,created_at):
        self._validate_id(id)

        self.id = id
        self.username = username
        self.role = role
        self.created_at = created_at

    def _validate_id(self, id):
        logging.basicConfig(level=logging.INFO)
        validPattern = r'^\d{6,11}$'
        log = logging.getLogger("myapp")
        log.info("/validId 컨트롤러로 들어온 Id=%s", id)
        if re.match(validPattern, str(self.id)) is None:
            log.info("/validId 컨트롤러로 들어온 Id가 regex를 만족하지 않음.")
            raise ValueError("/validId 컨트롤러로 들어온 Id가 regex를 만족하지 않음.")

    def _validate_pw(self, password):
        logging.basicConfig(level=logging.INFO)
