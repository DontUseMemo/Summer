from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait

# 크롬 주소 :
# C:\Program Files (x86)\Google\Chrome\Application\chrome.exe --remote-debugging-port=9222 --user-data-dir="C:/ChromeTEMP"

# 크롬 디버깅 모드 진입을 위한 포트번호 설정
chrome_options = Options()
chrome_options.add_experimental_option("debuggerAddress", "127.0.0.1:9222")

# 크롬 드라이버로 url 주소 진입
chrome_driver = 'D:/chromedriver.exe'
driver = webdriver.Chrome(chrome_driver, options=chrome_options)
driver.get('https://datalab.naver.com/keyword/realtimeList.naver?where=main')

driver.find_element_by_class_name('select_date')

title_list = driver.find_elements_by_css_selector('div.select_date span.title')

for keyword in title_list:
    print(keyword.text)
