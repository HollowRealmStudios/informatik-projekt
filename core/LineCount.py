from pathlib import Path

files = 0
complete = 0

for filename in Path('src').glob('**/*.java'):
    files += 1
    string = str(filename)
    if string.endswith(".java"):
        file = open(string, 'r')
        lines = file.readlines()
        print(string + ": " + (' ' * (80 - len(string))) + str(len(lines)))
        complete += len(lines)
        file.close()
print("-" * 90)
print(str(files) + " files: " + " " * 72 + str(complete))