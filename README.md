# blog-server

## 📌 개발 전 필수사항

```shell
# 하고 싶은 이슈를 골라 이슈 내에서 브랜치를 만들고 해당 브랜치와 연결한다
git branch -m "your-branch"
git pull origin "your-branch"

# 가상환경을 설정한다
# 우리는 현재 python3.9 버전을 사용합니다
python3.9 -m venv .

# requirements.txt 내부의 파일을 다운받는다
pip3 install requirements.txt

# 개발을 하고 requirements.txt를 저장한다
pip3 freeze > requirements.txt
```