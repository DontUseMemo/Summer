import subprocess
import time

import requests
from selenium import webdriver
from selenium.webdriver.chrome.options import Options

# 크롬창 자동으로 켜지게 설정
# subprocess.Popen(r'C:\Program Files (x86)\Google\Chrome\Application\chrome.exe'
#                  r' --remote-debugging-port=9222 --user-data-dir="C:\chrometemp"')

# 크롬 디버깅 모드 진입을 위한 포트번호 설정
chrome_options = Options()
# chrome_options.add_experimental_option("debuggerAddress", "127.0.0.1:9222")
# chrome_options.add_argument('--proxy-server=socks5://127.0.0.1:9150')
chrome_options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36")

# 크롬 드라이버로 프래그런티카 주소 진입
chrome_driver = 'D:/chromedriver.exe'
driver = webdriver.Chrome(chrome_driver, options=chrome_options)
driver.get("https://www.fragrantica.com/")
driver.implicitly_wait(10)

url = 'http://icanhazip.com/'

# proxies = {
#     'http': 'socks5://127.0.0.1:9050',
#     'https': 'socks5://127.0.0.1:9050',
# }

res = requests.get(url)
print(res.text)



