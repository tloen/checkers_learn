import random

def randspace():
	return random.random() * 2 - 1

def randpoint():
	return (randspace(), randspace(), 1)
	
def crossproduct(a, b):
	return a[0]*b[1] - a[1]*b[0]
	
def diff(a, b):
	return (a[0] - b[0], a[1] - b[1])
	
def dot(a, b):
	return sum(x*y for x,y in zip(a, b))
	
def sign(x):
	if x == 0:
		return 0
	if x > 0:
		return 1
	else:
		return -1
	
def side(p):
	return sign(crossproduct(diff(p2, p1), diff(p, p1)));	

N = 100
trials = 1000000
steps = 0.0

for t in range(trials):
	p1, p2 = randpoint(), randpoint()
	points = [randpoint() for i in range(N)]
	onside = [side(p) for p in points]

	w = [0, 0, 0]

	def g(x):
		return sign(dot(w, x))
		
	def misclassified():
		return [p for p in points if g(p) != side(p)]

	while misclassified():
		x = misclassified()[0]
		for i in range(3):
			w[i] += x[i] * side(x)
		steps = steps + 1
		
	if t % 10 == 9:
		print steps/t








