import requests
from datetime import datetime

class SWNoticeCrawler:
    def __init__(self):
        self.api_url = "https://swapi.cbnu.ac.kr/v1/notice/?page=1&limit=100&sort=-createdAt"
        self.base_url = "https://sw7up.cbnu.ac.kr/community/notice/"

    def get_notices(self):
        response = requests.get(self.api_url)
        if response.status_code == 200:
            data = response.json()
            notices = data["data"]["documents"]
            
            notice_title = []
            notice_link = []
            create_dates = []
            
            for item in notices:
                notice_title.append(item["title"])
                notice_link.append(self.base_url + item["_id"])
                create_dates.append(datetime.strptime(item['createdAt'][:10], '%Y-%m-%d').strftime('%y.%m.%d.'))
            
            return {
                'titles': notice_title,
                'links': notice_link,
                'dates': create_dates
            }
        else:
            print(f"Failed to fetch data. Status code: {response.status_code}")
            return None

if __name__ == "__main__":
    crawler = SWNoticeCrawler()
    results = crawler.get_notices()
    
    if results:
        print("\n=== 소중단 공지사항 ===")
        print("=" * 40)
        
        for title, link, date in zip(results['titles'], results['links'], results['dates']):
            print(f"\n제목: {title}")
            print(f"링크: {link}")
            print(f"날짜: {date}")
            print("-" * 40)