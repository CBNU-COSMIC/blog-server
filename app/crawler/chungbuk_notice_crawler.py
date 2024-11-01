from bs4 import BeautifulSoup
import requests

class ChungbukNoticeCrawler:
    def __init__(self):
        self.base_url = "https://www.chungbuk.ac.kr/www" 
        self.list_url = f"{self.base_url}/selectBbsNttList.do?key=813&bbsNo=8&pageUnit=10&searchCnd=all&pageIndex=1"

    def clean_link(self, link):
        if ';JSESSIONID=' in link:
            parts = link.split(';JSESSIONID=')
            link = parts[0] + '?' + parts[1].split('?', 1)[1]
        
        link = link.replace('www./', 'www/')
        return link

    def get_notices(self):
        try:
            response = requests.get(self.list_url)
            soup = BeautifulSoup(response.text, 'html.parser')
            
            rows = soup.select("tbody tr")
            
            create_dates = []
            notice_title = []
            notice_link = []
            
            for row in rows:
                # 제목과 링크 추출
                title_element = row.select_one("td.p-subject a")
                if title_element:
                    # 제목 추출
                    title = title_element.get_text(strip=True)
                    notice_title.append(title)
                    
                    # href 속성에서 링크 추출
                    link = title_element.get('href', '')
                    if link:
                        full_link = f"{self.base_url}{link}"
                        clean_link = self.clean_link(full_link)
                        notice_link.append(clean_link)
                    else:
                        notice_link.append(None)
                
                # 작성일 추출
                date_element = row.select_one("td:nth-child(6)")
                if date_element:
                    date = date_element.get_text(strip=True)
                    create_dates.append(date)
            
            return {
                'titles': notice_title,
                'links': notice_link,
                'dates': create_dates
            }
            
        except Exception as e:
            print(f"크롤링 중 오류 발생: {e}")
            return None

if __name__ == "__main__":
    crawler = ChungbukNoticeCrawler()
    results = crawler.get_notices()
    if results:
        print("=== 충북대학교 공지사항 ===")
        for title, link, date in zip(results['titles'], results['links'], results['dates']):
            print(f"제목: {title}")
            print(f"링크: {link}")
            print(f"작성일: {date}")
