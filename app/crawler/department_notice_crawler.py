from bs4 import BeautifulSoup
import requests
from datetime import datetime

class DepartmentNoticeCrawler:
    def __init__(self):
        self.url = "https://computer.chungbuk.ac.kr/bbs/bbs.php?db=notice&pgID=ID12415888101"

    def get_notices(self):
        try:
            response = requests.get(self.url)
            soup = BeautifulSoup(response.text, 'html.parser')
            
            notices = soup.select("nobr > a")
            dates = soup.find_all('td', {'class': 'body_num', 'style': 'padding:2 0 0 0;'})
            
            create_dates = []
            notice_title = []
            notice_link = []
            
            # 날짜 정보 추출
            for i in range(2, len(dates), 4):
                create_dates.append(dates[i].text.strip())
            
            # 제목과 링크 추출
            for notice, date in zip(notices, create_dates):
                href = notice.get('href')
                title = ''.join([text for text in notice.stripped_strings])
                
                notice_title.append(title)
                notice_link.append(f"{self.url}{href}")
            
            return {
                'titles': notice_title,
                'links': notice_link,
                'dates': create_dates
            }
            
        except Exception as e:
            print(f"크롤링 중 오류 발생: {e}")
            return None

if __name__ == "__main__":
    crawler = DepartmentNoticeCrawler()
    results = crawler.get_notices()
    if results:
        print("=== 충북대학교 컴퓨터공학과 공지사항 ===")
        print("제목:", results['titles'])
        print("링크:", results['links'])
        print("날짜:", results['dates'])