from colorama import Fore, Style, init
init()

array = []

print("How long would you like array to be?")
amount = int(input())

for i in range(amount):
    print("enter ", (i + 1), " integer: ")
    value = int(input())
    array.append(value)

print("you array is: ", array)

for j in range(len(array)):
    for i in range(len(array) -1):
        if array[i] > array[i+1]:
            array[i] = array[i+1]
            array[i+1] = array[i]

print(Fore.BLUE, "Sorted array: ", array)