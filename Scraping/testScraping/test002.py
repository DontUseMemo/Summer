# step1.selenium 패키지와 time 모듈 import
import subprocess

from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
import time
# 이미지 파일을 저장할 폴더 경로 설정
import os
# 이미지 저장을 위한 라이브러리
from urllib.request import urlretrieve

# step2.검색할 키워드 입력
query = input('검색할 키워드를 입력하세요: ')

# 크롬창 자동으로 켜지게 설정
subprocess.Popen(r'C:\Program Files (x86)\Google\Chrome\Application\chrome.exe'
                 r' --remote-debugging-port=9222 --user-data-dir="C:\chrometemp"')

# 크롬 디버깅 모드 진입을 위한 포트번호 설정
chrome_options = Options()
chrome_options.add_experimental_option("debuggerAddress", "127.0.0.1:9222")

# 크롬 드라이버로 url 주소 진입
chrome_driver = 'D:/chromedriver.exe'
driver = webdriver.Chrome(chrome_driver, options=chrome_options)
driver.get('https://www.naver.com/')
time.sleep(3)

# step4.검색창에 키워드 입력 후 엔터
search_box = driver.find_element_by_css_selector("input#query")
search_box.send_keys(query)
search_box.send_keys(Keys.RETURN)
time.sleep(2)

# step5.뉴스 탭 클릭
driver.find_element_by_xpath('//*[@id="lnb"]/div[1]/div/ul/li[2]/a').click()
time.sleep(2)

# step6.뉴스 제목 텍스트 추출
news_titles = driver.find_elements_by_css_selector(".news_tit")

for i in news_titles:
    title = i.text
    print(title)

# step7.뉴스 하이퍼링크 추출
for i in news_titles:
    href = i.get_attribute('href')
    print(href)

# step8.뉴스 썸네일 이미지 추출
news_thumbnail = driver.find_elements_by_css_selector("img.thumb.api_get")

link_thumbnail = []

for img in news_thumbnail:
    link_thumbnail.append(img.get_attribute('src'))

# path_folder의 경로는 각자 저장할 폴더의 경로를 적어줄 것(ex.img_download)
path_folder = 'c:/Users/patbingsu/Desktop/img_save/img/'

if not os.path.isdir(path_folder):
    os.mkdir(path_folder)

# 이미지 다운로드
i = 0

for link in link_thumbnail:
    i += 1
    urlretrieve(link, path_folder + f'{i}.jpg')  # link에서 이미지 다운로드, './imgs/'에 파일명은 index와 확장자명으로
