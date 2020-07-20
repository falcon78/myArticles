package Chapter1.Section3;

public class TextEditorBuffer {
    public static void main(String[] args) throws IllegalAccessException {
        TextEditorBuffer textEditor = new TextEditorBuffer();

        textEditor.insert("let a=0".toCharArray());
        assert textEditor.toString().equals("let a=0");
        textEditor.delete();
        textEditor.insert('1');
        assert textEditor.toString().equals("let a=1");

        for (int i = 0; i < textEditor.size(); i++) {
            textEditor.left();
        }
        assert textEditor.leftBuffer.size() == 0;
        assert textEditor.rightBuffer.size() == 7;

        for (int i = 0; i < 3; i++) {
            textEditor.right();
        }
        for (int i = 0; i < 3; i++) {
            textEditor.delete();
        }
        textEditor.insert("const".toCharArray());
        assert textEditor.toString().equals("const a=1");

        textEditor.right();
        textEditor.right();
        textEditor.delete();
        textEditor.insert('b');
        assert textEditor.toString().equals("const b=1");

        textEditor.jumpToStart();
        textEditor.insert("// ".toCharArray());
        assert textEditor.toString().equals("// const b=1");

        textEditor.jumpToEnd();
        textEditor.insert('2');
        assert textEditor.toString().equals("// const b=12");
    }

    Stack<Character> leftBuffer;
    Stack<Character> rightBuffer;

    public TextEditorBuffer() {
        leftBuffer = new Stack<>();
        rightBuffer = new Stack<>();
    }

    public void insert(char c) {
        leftBuffer.push(c);
    }

    public void insert(char[] arr) {
        for (char c : arr)
            leftBuffer.push(c);
    }

    public char delete() throws IllegalAccessException {
        if (leftBuffer.isEmpty()) return ' ';
        return leftBuffer.pop();
    }

    public void left() throws IllegalAccessException {
        if (leftBuffer.isEmpty()) return;
        rightBuffer.push(leftBuffer.pop());
    }

    public void right() throws IllegalAccessException {
        if (rightBuffer.isEmpty()) return;
        leftBuffer.push(rightBuffer.pop());
    }

    public void jumpToEnd() throws IllegalAccessException {
        while (!rightBuffer.isEmpty()) {
            leftBuffer.push(rightBuffer.pop());
        }
    }

    public void jumpToStart() throws IllegalAccessException {
        while (!leftBuffer.isEmpty()) {
            rightBuffer.push(leftBuffer.pop());
        }
    }

    public int size() {
        return leftBuffer.size() + rightBuffer.size();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char c : rightBuffer) {
            builder.append(c);
        }
        builder.reverse();
        for (char c : leftBuffer) {
            builder.append(c);
        }
        builder.reverse();
        return builder.toString();
    }
}
