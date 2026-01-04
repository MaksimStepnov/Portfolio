from colorama import Fore, Style, init
from datetime import datetime
import requests
import time
import random
from bs4 import BeautifulSoup
init()

USER_AGENTS = [
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64)",
    "Mozilla/5.0 (X11; Linux x86_64)",
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)"]

print(Fore.LIGHTBLUE_EX, "|------OSINT TOOLKIT------|")
print(Fore.LIGHTGREEN_EX, "|-----Windows edition-----|")
print(Fore.LIGHTGREEN_EX, datetime.now())
print("")

print(Fore.LIGHTMAGENTA_EX, "Always enter 1 if detail is unknown. ")
print(Fore.RESET, "[+] Target's name:")
name = input()
if name == "1":
    b_name = False

print("[+] Phone number (with country code, eg: +7): ")
phone = input()
if phone == "1":
    b_phone = False

print("[+] email address: ")
email = input()
if email == "1":
    b_email = False

print(Fore.LIGHTMAGENTA_EX, "Any known social media accounts? (eg: tiktok, telegram etc..): ")
print(Fore.LIGHTMAGENTA_EX, "Always enter username, not display name, with @, (eg: @Zabroshka): ")

print(Fore.RESET, "Linkedin: ")
linkedin = input()
if linkedin == "1":
    linkedin = False

print("youtube: ")
youtube = input()
if youtube == "1":
    youtube = False

print("Vkontakte (VK): ")
vkontakte = input()
if vkontakte == "1":
    1 == "unknown"
    b_vkontakte = False

print("Any known common username? (eg: @Zabroshka): ")
username = input()
if username == "1":
    username = False

inf_array = [name, phone, email, linkedin, youtube, vkontakte]
start_time = time.time()

print(Fore.LIGHTMAGENTA_EX, "OK, this is the information you entered: ")
print("")
print(Fore.LIGHTBLUE_EX, "Identity profile:")
for i in range(5):
    print(Fore.LIGHTBLUE_EX, (i + 1), ";", inf_array[i])
print("")
print(Fore.LIGHTMAGENTA_EX, "Starting deep web scan... Please wait...")

def fetch(url, timeout=10):
    headers = {
        "User-Agent": random.choice(USER_AGENTS),
        "Accept-Language": "en-US,en;q=0.9",
        "Connection": "close"}
    
    time.sleep(random.uniform(1.2, 3.0))
    try:
        response = requests.get(url, headers=headers, timeout=timeout)
        return response
    except requests.RequestException:
        return None

def scan_vk(vkontake):
    if not vkontake or vkontake == "1":
        return None
    if vkontake.startswith("@"):
        vkontake = vkontake[1:]
    url = f"https://vk.com/{vkontake}"
    r = fetch(url)
    if not r or r.status_code != 200:
        return None
    if "page_not_found" in r.text.lower():
        return None
    soup = BeautifulSoup(r.text, "html.parser")
    name = soup.find("h1", class_="page_name")
    status = soup.find("div", class_="profile_status")
    photo = soup.find("img", class_="page_avatar_img")
    return {
        "platform": "VK",
        "url": url,
        "confidence": "HIGH",
        "data": {
            "name": name.text.strip() if name else None,
            "status": status.text.strip() if status else None,
            "profile_photo": photo["src"] if photo else None}}
    
def scan_vk_name(name):
    if not name or name == "1":
        return None
    if name.startswith("@"):
        name = name[1:]
    url = f"https://vk.com/{name}"
    r = fetch(url)
    if not r or r.status_code != 200:
        return None
    if "page_not_found" in r.text.lower():
        return None
    soup = BeautifulSoup(r.text, "html.parser")
    name = soup.find("h1", class_="page_name")
    status = soup.find("div", class_="profile_status")
    photo = soup.find("img", class_="page_avatar_img")
    return {
        "platform": "VK",
        "url": url,
        "confidence": "HIGH",
        "data": {
            "name": name.text.strip() if name else None,
            "status": status.text.strip() if status else None,
            "profile_photo": photo["src"] if photo else None}}

def scan_youtube(youtube):
    url = f"https://www.youtube.com/@{youtube}"
    r = fetch(url)
    if not r or r.status_code != 200:
        return None
    if "This page isn't available" in r.text:
        return None
    soup = BeautifulSoup(r.text, "html.parser")
    title = soup.find("meta", property="og:title")
    description = soup.find("meta", property="og:description")
    return {
        "platform": "YouTube",
        "url": url,
        "confidence": "HIGH",
        "data": {
            "channel_name": title["content"] if title else None,
            "description": description["content"] if description else None}}

def scan_youtube(username):
    if not username or username == "1":
        return None
    if username.startswith("@"):
        username = username[1:]
    url = f"https://www.youtube.com/@{username}"
    r = fetch(url)
    if not r or r.status_code != 200:
        return None
    if "This page isn't available" in r.text:
        return None
    soup = BeautifulSoup(r.text, "html.parser")
    title = soup.find("meta", property="og:title")
    description = soup.find("meta", property="og:description")
    return {
        "platform": "YouTube",
        "url": url,
        "confidence": "HIGH",
        "data": {
            "channel_name": title["content"] if title else None,
            "description": description["content"] if description else None}}

def linkedin_sensor(name):
    if not name or name == "1":
        return None

    query = f'site:linkedin.com/in "{name}"'

    return {
        "platform": "LinkedIn",
        "confidence": "MEDIUM",
        "method": "Search Engine OSINT",
        "query": query
    }


results = []
results.append(linkedin_sensor(name))
results.append(scan_youtube(youtube))
results.append(scan_youtube(username))
results.append(scan_vk(vkontakte))
results.append(scan_vk(name))

end_time = time.time()
scan_time = end_time - start_time
print(Fore.LIGHTMAGENTA_EX, f"\nScan finished in {scan_time:.2f} seconds\n")

for res in results:
    if not res:
        continue
    print(Fore.LIGHTGREEN_EX, f"[+] {res['platform']}")
    for k, v in res.items():
        if k != "platform":
            print("   ", k, ":", v)
    print()


