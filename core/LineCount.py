from pathlib import Path

complete = 0

for filename in Path('src').glob('**/*.java'):
    string = str(filename)
    if string.endswith(".java"):
        file = open(string, 'r')
        lines = file.readlines()
        print(string + ": " + (' ' * (80 - len(string))) + str(len(lines)))
        complete += len(lines)
        file.close()
print("-" * 90)
print(" " * 80 + str(complete))