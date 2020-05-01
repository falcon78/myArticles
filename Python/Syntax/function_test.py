def test(one, two, *args, three):
    print(one, two, three, args)


test('one', 'two', 'args', 'three')