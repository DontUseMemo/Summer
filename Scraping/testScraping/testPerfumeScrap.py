import subprocess

import cx_Oracle
import requests as requests
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
import time
# 이미지 파일을 저장할 폴더 경로 설정
import os
# 이미지 저장을 위한 라이브러리
from urllib.request import urlretrieve, urlopen

# 오라클 클라우드 연결
# cx_Oracle.init_oracle_client(lib_dir=r'D:/instantclient_21_6')
# connection = cx_Oracle.connect(user='admin', password='Project123456', dsn='perfumesinfo_low')
# OracleCursor = connection.cursor()

# 데이터베이스에 저장해보기
# OracleCursor.execute('create table python (name varchar2(20), data varchar2(20))')
# rows = [(12356)]
# rows = ('dfe', 'dfef')
# OracleCursor.execute("insert into python(name, data) values (:1, :2)", rows)
# connection.commit()

# 크롬창 자동으로 켜지게 설정
subprocess.Popen(r'C:\Program Files (x86)\Google\Chrome\Application\chrome.exe'
                 r' --remote-debugging-port=9222 --user-data-dir="C:\chrometemp"')

# 크롬 디버깅 모드 진입을 위한 포트번호 설정
chrome_options = Options()
chrome_options.add_experimental_option("debuggerAddress", "127.0.0.1:9222")

# 크롬 드라이버로 url 주소 진입
chrome_driver = 'D:/chromedriver.exe'
driver = webdriver.Chrome(chrome_driver, options=chrome_options)

# 프레그런티카 진입 (시간은 사람인 척 하기 위해 수동으로 조절)
driver.get('https://www.fragrantica.com/search/')
time.sleep(4)

# 향수 상세 리스트 가져오기
perfume_links = driver.find_elements_by_css_selector('.card-section > p > a')
links = []

# 선택된 태그에서 상세 링크 저장하기
for i in perfume_links:
    link = i.get_attribute('href')
    links.append(link)

# 향수 제목 리스트 가져오기
perfume_titles = driver.find_elements_by_css_selector('.cell.card.fr-news-box div:nth-child(2) p a')
titles = []

# 선택된 태그에서 향수 제목 저장하기
for i in perfume_titles:
    titles.append(i.text)
    print(i.text)

# 상세페이지에서 각 정보 출력하는 반복문
for click_link in links:
    # 각 향수 상세정보 링크로 진입
    driver.get(click_link)
    time.sleep(5)
    print('-' * 50 + '\n')

    # 향수 젠더 출력
    name = driver.find_element_by_css_selector('#toptop > h1 small')
    print(name.text)

    # 향수 브랜드 출력
    brand = driver.find_element_by_xpath('//*[@id="main-content"]/div[1]/div[1]/div/div[2]/div[1]/div[2]/p/a/span')
    print(brand.text)

    # 향의 조합 (accord) 출력
    accords = driver.find_elements_by_css_selector('.accord-bar')
    for i in accords:
        accord = i.get_attribute('style')
        accordChangeString = str(accord)
        accordWidth = accordChangeString.split(" ")
        print(i.text + ': ' + accordWidth.pop(11))

    # 향수 소지 여부 출력
    possession = driver.find_elements_by_css_selector('#rating > div div div div:nth-child(2) span')
    possession_ratio = driver.find_elements_by_css_selector('#rating > div div div div.voting-small-chart-size div div')
    for i in possession:
        possess = i.text
        print(possess)
    for j in possession_ratio:
        ratio = j.get_attribute('style')
        print(ratio)

    # 향수 선호도와 향수 무드 출력
    preferences = driver.find_elements_by_css_selector('.cell.small-6 > div div .voting-small-chart-size > div > div')
    count = 0
    for i in preferences:
        preference = i.get_attribute('style')
        if count < 5:
            print('선호도: ' + preference)
        else:
            print('향수 무드: ' + preference)
        count += 1

    # 향수 설명글 출력
    perfume_article = driver.find_element_by_xpath('//*[@id="main-content"]/div[1]/div[1]/div/div[2]/div[5]/div/p[1]')
    print(perfume_article.text)

    # 조향사 1명 이상 출력
    perfumers = driver.find_elements_by_css_selector('#main-content > div.grid-x.grid-margin-x > div.small-12.medium-12'
                                                     '.large-9.cell > div > div:nth-child(3) > div:nth-child(2) div a')
    for i in perfumers:
        print(i.text)

    # 향수 노트 세 종류 구분해서 출력
    # --탑 노트
    top_note_title = driver.find_element_by_css_selector('#pyramid > div:nth-child(1) > div > '
                                                         'div:nth-child(2) > h4:nth-child(3)')
    top_notes = driver.find_elements_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                     'div:nth-child(4) > div div div:nth-child(2)')
    print(top_note_title.text)
    for i in top_notes:
        print(i.text)

    # --미들 노트
    middle_note_title = driver.find_element_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                            'h4:nth-child(5)')
    middle_notes = driver.find_elements_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                        'div:nth-child(6) > div div div:nth-child(2)')
    print(middle_note_title.text)
    for i in middle_notes:
        print(i.text)

    # --베이스 노트
    base_note_title = driver.find_element_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                          'h4:nth-child(7)')
    base_notes = driver.find_elements_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                      'div:nth-child(8) > div div div:nth-child(2)')
    print(base_note_title.text)
    for i in base_notes:
        print(i.text)

    # 향수 이미지 다운로드(현재는 이미지 다운받는 주소만 출력중)
    perfume_img = driver.find_element_by_xpath('//*[@id="main-content"]/div[1]/div[1]/div/div[2]/div[1]/'
                                               'div[1]/div/div/img').get_attribute('src')
    print(perfume_img)
