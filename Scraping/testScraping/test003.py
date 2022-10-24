import subprocess

import requests as requests
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
import time
# 이미지 파일을 저장할 폴더 경로 설정
import os
# 이미지 저장을 위한 라이브러리
from urllib.request import urlretrieve, urlopen

# 크롬창 자동으로 켜지게 설정
subprocess.Popen(r'C:\Program Files (x86)\Google\Chrome\Application\chrome.exe'
                 r' --remote-debugging-port=9222 --user-data-dir="C:\chrometemp"')

# 크롬 디버깅 모드 진입을 위한 포트번호 설정
chrome_options = Options()
chrome_options.add_experimental_option("debuggerAddress", "127.0.0.1:9222")

# 크롬 드라이버로 url 주소 진입
chrome_driver = 'D:/chromedriver.exe'
driver = webdriver.Chrome(chrome_driver, options=chrome_options)

driver.get('https://www.fragrantica.com/')
time.sleep(3)

driver.find_element_by_css_selector('.show-for-large a').click()
time.sleep(3)

# 향조 그룹명 출력
group_title = driver.find_elements_by_css_selector('[id*=groupnotes_group_] > div > h2')

for i in group_title:
    title = i.text
    print(title)
