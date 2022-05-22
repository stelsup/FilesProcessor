# FileProcessor

Простенькая консольная java-утилита для "препроцессинга" текстовых файлов.
Краткое описание условий задачи:
```
Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки. В других папках также могут находится текстовые файлы и папки (уровень вложенности может оказаться любым). Найти все текстовые файлы, отсортировать их по имени и склеить содержимое в один текстовый файл. 
В каждом файле может быть ни одной, одна или несколько директив формата:

*require ‘<путь к другому файлу от корневого каталога>’*

Директива означает, что текущий файл зависит от другого указанного файла. Необходимо выявить все зависимости между файлами, построить сортированный список, для которого выполняется условие: если файл А, зависит от файла В, то файл А находится ниже файла В в списке. Осуществить конкатенацию файлов в соответствии со списком. Если такой список построить невозможно (существует циклическая зависимость), программа должна вывести соответствующее сообщение.
```

Перечень расширений обрабатываемых файлов считывается из файла **.extensions**.

Примеры работы приведены ниже. Содержимое папок __Test__ и __Test2__ залито в репозиторий.

## Пример 1.
**Input file tree.**
```
Test
|   A.txt
|   
+---A
|   |   AA.txt
|   |   
|   +---AA
|   |   |   AAA.txt
|   |   |   AAB.txt
|   |   |   
|   |   \---AAA
|   |           z.txt
|   |           
|   \---AB
\---B
        p.txt
```
**Output:**
```
A.txt
<B/p.txt>
<A/AA.txt>
<A/AA/AAB.txt>
<A/AA/AAA.txt begin>
<A/AA/AAA.txt end>
<A/AA/AAA/z.txt>
```
## Пример 2 - работа с циклической вложенностью:
**Input file tree:**
```
Test2
|   A.txt
|   B.txt    
```
**Output:**
```
<AA.txt>
<B.txt>
<A.txt>
```
