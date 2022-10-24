import subprocess
import time

from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# driver = webdriver.Chrome(r'D:/chromedriver.exe')
#
# subprocess.Popen(r'C:\Program Files (x86)\Google\Chrome\Application\chrome.exe'
#                  r' --remote-debugging-port=9222 --user-data-dir="C:\chrometemp"')
#
# option = Options()
# option.add_experimental_option("debuggerAddress", "127.0.0.1:9222")
#
# chrome_ver = chromedriver_autoinstaller.get_chrome_version().split('.')[0]
# try:
#     driver = webdriver.Chrome(f'./{chrome_ver}/chromedriver.exe', options=option)
# except:
#     chromedriver_autoinstaller.install(True)
#     driver = webdriver.Chrome(f'./{chrome_ver}/chromedriver.exe', options=option)
# driver.implicitly_wait(10)

# 크롬 주소 :
# C:\Program Files (x86)\Google\Chrome\Application\chrome.exe --remote-debugging-port=9222 --user-data-dir="C:/ChromeTEMP"

# 크롬 디버깅 모드 진입을 위한 포트번호 설정
chrome_options = Options()
chrome_options.add_experimental_option("debuggerAddress", "127.0.0.1:9222")

# 크롬 드라이버로 프래그런티카 주소 진입
chrome_driver = 'D:/chromedriver.exe'
driver = webdriver.Chrome(chrome_driver, options=chrome_options)
driver.get("https://papago.naver.com/")
time.sleep(3)

# Implicitly wait을 5초로 설정하면 페이지가 로딩되는데 5초까지 기다린다.
# 만약 페이지 로딩이 2초에 완료되었다면 더 기다리지 않고 다음 코드를 수행한다.
# 기본 설정은 0초로 되어있고, 한번만 설정하면 driver를 사용하는 모든 코드에 적용된다.
# driver.implicitly_wait(5)

# 번역할 문장 입력
driver.find_element_by_css_selector('#sourceEditArea textarea').send_keys('i love koala study')
time.sleep(3)

# 번역 버튼 클릭
driver.find_element_by_css_selector('#btnTranslate').click()
time.sleep(5)

korean_translate = driver.find_element_by_css_selector('#txtTarget span')
time.sleep(4)

print(korean_translate)

# driver.find_element_by_xpath('//*[@id="offCanvasLeft"]/ul/li[6]/a').send_keys(Keys.ENTER)
#
# time.sleep(5)
# driver.close()
# try:
#     wait.until(EC.presence_of_element_located((By.CLASS_NAME, 'gLFyf')))
# finally:
#     driver.quit()
