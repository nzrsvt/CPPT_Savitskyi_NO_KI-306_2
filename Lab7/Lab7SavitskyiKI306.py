rows_num = int(input("Enter number of rows: "))

lst = []

filler = input("Enter filler symbol: ")

endPtr = 0
mid = int (rows_num / 2)

for r in range(rows_num):
    lst.append([])

for i in range(mid+1): 
    for l in range(mid+endPtr+1):
        lst[i].append(' ')
    for j in range(mid - i, mid + i + 1, 1):
        lst[i][j]=filler
    endPtr += 1
    print(lst[i])

endPtr-=2

for i in range(mid + 1, rows_num, 1):
    endPtr -= 1
    for l in range(mid+endPtr+2):
        lst[i].append(' ')
    for j in range(i - mid, rows_num - (i - mid) , 1):
        lst[i][j]=filler
    print(lst[i])