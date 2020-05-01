from collections import defaultdict
import json

tree = lambda: defaultdict(tree)

a = defaultdict(tree)

a['test'] = {'test': 1}
a['test']['test2'] = {'test' : 3}
a['oop']['oop2']['oop3'] = {'test': 9}

print(json.dumps(a))