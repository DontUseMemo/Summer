import random
import subprocess

import cx_Oracle
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
import time
# 이미지 파일을 저장할 폴더 경로 설정
import os
# 이미지 저장을 위한 라이브러리
from urllib.request import urlretrieve

# 파일을 실행하기 전에 윈도우 + R 눌러서 실행창에 해당 주소 입력해서 프래그런티카 진입해서 리캡챠 클릭할 것
# C:\Program Files (x86)\Google\Chrome\Application\chrome.exe --remote-debugging-port=9222 --user-data-dir="C:/ChromeTEMP"

# 오라클 클라우드 연결
cx_Oracle.init_oracle_client(lib_dir=r'./resource/instantclient_21_6')
connection = cx_Oracle.connect(user='admin', password='Project123456', dsn='perfumesinfo_low')
oracleCursor = connection.cursor()

# 크롬창 자동으로 켜지게 설정
subprocess.Popen(r'C:\Program Files (x86)\Google\Chrome\Application\chrome.exe'
                 r' --remote-debugging-port=9222 --user-data-dir="C:\chrometemp"')

# 크롬 디버깅 모드 진입을 위한 포트번호 설정
chrome_options = Options()
chrome_options.add_experimental_option("debuggerAddress", "127.0.0.1:9222")

# 크롬 드라이버로 url 주소 진입
chrome_driver = './resource/chromedriver.exe'
driver = webdriver.Chrome(chrome_driver, options=chrome_options)

# 프레그런티카 진입 (시간은 사람인 척 하기 위해 수동으로 조절)
driver.get('https://www.fragrantica.com/search/')
time.sleep(5)

# oracleCursor.execute('drop table perfume_info')
# oracleCursor.execute('drop table accords')
# oracleCursor.execute('drop table perfume_note')
# oracleCursor.execute('drop table perfumer_list')
# print('테이블 삭제')
# connection.commit()
#
#
# # 데이테베이스 테이블 생성(속성 순서 변하면 안 됨)
# # --향수 테이블
# oracleCursor.execute('create table perfume_info(perfume_name varchar2(70), gender varchar2(18), brand varchar2(40), '
#                      'have varchar2(9), had varchar2(9), want varchar2(9), love varchar2(9), likes varchar2(9), '
#                      'ok varchar2(9), dislike varchar2(9), hate varchar2(9), winter varchar2(9), spring varchar2(9), '
#                      'summer varchar2(9), fall varchar2(9), days varchar2(9), night varchar2(9), '
#                      'article varchar2(700), img varchar2(74))')
# # --향수 조합 테이블
# oracleCursor.execute('create table accords(perfume_name varchar2(70), scent_name varchar2(30), ratio varchar2(9))')
# # --사용한 향조 테이블
# oracleCursor.execute('create table perfume_note(perfume_name varchar2(70), note_group varchar2(30), note varchar2(50))')
# # --향수 조향사 테이블
# oracleCursor.execute('create table perfumer_list(perfume_name varchar2(70), perfumer varchar2(40))')
# print('테이블 생성 완료')
# connection.commit()

# 향수 상세 리스트 가져오기
perfume_links = driver.find_elements_by_css_selector('.card-section > p > a')
links = []

# 향수 저장할 숫자만큼 자바스크립트 버튼 누르기
while True:
    if len(perfume_links) < 30:
        driver.find_element_by_css_selector('.grid-x.grid-margin-x.grid-margin-y.text-center .cell.small-12 button')\
            .send_keys(Keys.ENTER)
        perfume_links = driver.find_elements_by_css_selector('.card-section > p > a')
        time.sleep(6)
    else:
        break

# 선택된 태그에서 상세 링크 저장하기
for i in perfume_links:
    link = i.get_attribute('href')
    links.append(link)

# 향수 제목 리스트 가져오기
perfume_names = driver.find_elements_by_css_selector('.cell.card.fr-news-box div:nth-child(2) p a')
name_list = []

# 선택된 태그에서 향수 제목 저장하기
for i in perfume_names:
    name_list.append(i.text)

# 이미 저장된 링크 거르기
index = 0
for i in name_list:
    sql = 'select perfume_name from perfume_info where perfume_name=:perfume_name'
    oracleCursor.execute(sql, {'perfume_name': i})
    record = oracleCursor.fetchone()
    if str(i) == 'Spicebomb Extreme':
        index += 1
        continue
    elif record is None:
        print(i + '부터 저장 시작')
        break
    else:
        index += 1
        continue
print('현재 저장된 향수: ' + str(index))
current_links = links[index:len(links)]

# 상세페이지에서 각 정보 출력하는 반복문
for click_link in current_links:
    # 각 테이블에 저장할 리스트
    insert_perfume_info = []
    insert_accords = []
    insert_perfume_note = []
    insert_perfumer_list = []

    # 각 향수 상세정보 링크로 진입
    driver.get(click_link)
    time.sleep(random.randint(9, 13))
    print('-' * 50 + '\n')

    # 향수 이름 저장
    name = driver.find_element_by_xpath('//*[@id="main-content"]/div[1]/div[1]/div/div[2]/div[5]/div/p[1]/b[1]')
    insert_perfume_info.append(name.text)
    print(name.text + '저장중...')

    # 향수 젠더 출력
    gender = driver.find_element_by_css_selector('#toptop > h1 small')
    insert_perfume_info.append(gender.text)

    # 향수 브랜드 출력
    brand = driver.find_element_by_xpath('//*[@id="main-content"]/div[1]/div[1]/div/div[2]/div[1]/div[2]/p/a/span')
    insert_perfume_info.append(brand.text)

    # 향수 소지 여부 출력
    possessions = driver.find_elements_by_css_selector('#rating > div div div div.voting-small-chart-size div div')
    for j in possessions:
        possession = j.get_attribute('style')
        possessionChangeString = str(possession)
        possessionWidth = possessionChangeString.split(" ")
        possession_ratio = possessionWidth.pop(9).strip('%;')
        insert_perfume_info.append(possession_ratio)

    # 향의 조합 (accord) 출력
    accords = driver.find_elements_by_css_selector('.accord-bar')
    for i in accords:
        accord = i.get_attribute('style')
        accordChangeString = str(accord)
        accordWidth = accordChangeString.split(" ")
        individual_accord = (name.text, i.text, accordWidth.pop(11).strip('%;'))
        insert_accords.append(individual_accord)

    # 향수 선호도와 향수 무드 출력
    preferences = driver.find_elements_by_css_selector('.cell.small-6 > div div .voting-small-chart-size > div > div')
    for i in preferences:
        preference = i.get_attribute('style')
        preferenceChangeString = str(preference)
        preferenceWidth = preferenceChangeString.split(" ")
        preference_ratio = preferenceWidth.pop(9).strip('%;')
        insert_perfume_info.append(preference_ratio)

    # 조향사 1명 이상 출력
    perfumers = driver.find_elements_by_css_selector('#main-content > div.grid-x.grid-margin-x > div.small-12.medium-12'
                                                     '.large-9.cell > div > div:nth-child(3) > div:nth-child(2) div a')
    for i in perfumers:
        perfumer = (name.text, i.text)
        insert_perfumer_list.append(perfumer)

    # 향수 노트 세 종류 구분해서 출력
    # --탑 노트
    top_note_title = driver.find_element_by_css_selector('#pyramid > div:nth-child(1) > div > '
                                                         'div:nth-child(2) > h4:nth-child(3)')
    top_notes = driver.find_elements_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                     'div:nth-child(4) > div div div:nth-child(2)')
    for i in top_notes:
        top_note = (name.text, top_note_title.text, i.text)
        insert_perfume_note.append(top_note)

    # --미들 노트
    middle_note_title = driver.find_element_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                            'h4:nth-child(5)')
    middle_notes = driver.find_elements_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                        'div:nth-child(6) > div div div:nth-child(2)')
    for i in middle_notes:
        middle_note = (name.text, middle_note_title.text, i.text)
        insert_perfume_note.append(middle_note)

    # --베이스 노트
    base_note_title = driver.find_element_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                          'h4:nth-child(7)')
    base_notes = driver.find_elements_by_css_selector('#pyramid > div:nth-child(1) > div > div:nth-child(2) > '
                                                      'div:nth-child(8) > div div div:nth-child(2)')
    for i in base_notes:
        base_note = (name.text, base_note_title.text, i.text)
        insert_perfume_note.append(base_note)

    # 향수 설명글 출력
    perfume_article = driver.find_element_by_xpath('//*[@id="main-content"]/div[1]/div[1]/div/div[2]/div[5]/div/p[1]')
    insert_perfume_info.append(perfume_article.text)

    # 향수 이미지 다운로드 및 출력
    perfume_img_link = driver.find_element_by_xpath('//*[@id="main-content"]/div[1]/div[1]/div/div[2]/div[1]/'
                                                    'div[1]/div/div/img').get_attribute('src')
    # --path_folder의 경로
    path_folder = './img/'

    if not os.path.isdir(path_folder):
        os.mkdir(path_folder)

    urlretrieve(perfume_img_link, path_folder + f'{name.text}.jpg')
    print('이미지 다운 완료')
    insert_perfume_info.append(name.text + '.jpg')

    print(insert_perfumer_list)
    print(insert_accords)
    print(insert_perfume_info)
    print(insert_perfume_note)

    # 저장하기
    # --조향사
    sql = 'insert into perfumer_list values (:1, :2)'
    oracleCursor.prepare(sql)
    oracleCursor.executemany(sql, insert_perfumer_list)

    # --향수
    sql = 'insert into perfume_info values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14, :15, :16, ' \
          ':17, :18, :19)'
    oracleCursor.prepare(sql)
    oracleCursor.execute(sql, insert_perfume_info)

    # --향수 조합(accords)
    sql = 'insert into accords values (:1, :2, :3)'
    oracleCursor.prepare(sql)
    oracleCursor.executemany(sql, insert_accords)

    # --향수 향조(note)
    sql = 'insert into perfume_note values (:1, :2, :3)'
    oracleCursor.prepare(sql)
    oracleCursor.executemany(sql, insert_perfume_note)

    connection.commit()
    print(name.text + '저장 완료')


driver.close()
driver.quit()
