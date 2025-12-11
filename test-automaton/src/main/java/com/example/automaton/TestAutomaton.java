package com.example.automaton;

public class TestAutomaton {

    public enum State {
        S0(0),
        S1(1),
        S2(2),
        S3(3),
        F(4);

        private final int code;

        State(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            if (this == F) {
                return "F";
            }
            return String.valueOf(code);
        }
    }

    private State state = State.S0;

    public void reset() {
        state = State.S0;
    }

    public State getState() {
        return state;
    }

    public State process(String input) {
        reset();
        if (input == null) {
            return state;
        }
        for (int i = 0; i < input.length(); i++) {
            step(input.charAt(i));
        }
        return state;
    }

    private void step(char c) {
        switch (state) {
            case S0:
                if (c == 'T') {
                    state = State.S1;
                } else {
                    state = State.S0;
                }
                break;

            case S1:
                if (c == 'E') {
                    state = State.S2;
                } else if (c == 'T') {
                    state = State.S1;
                } else {
                    state = State.S0;
                }
                break;

            case S2:
                if (c == 'S') {
                    state = State.S3;
                } else if (c == 'T') {
                    state = State.S1;
                } else {
                    state = State.S0;
                }
                break;

            case S3:
                if (c == 'T') {
                    state = State.F;
                } else {
                    state = State.S0;
                }
                break;

            case F:
                state = State.F;
                break;

            default:
                state = State.S0;
                break;
        }
    }

    public boolean hasTest(String input) {
        return process(input) == State.F;
    }
}
