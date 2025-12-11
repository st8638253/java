package com.example.automaton;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestAutomatonTest {

    @ParameterizedTest
    @CsvSource({
            "'', 0",
            "'a', 0",
            "'abc', 0",
            "'T', 1",
            "'TE', 2",
            "'TES', 3",
            "'TEST', 4",
            "'abcTESTabc', 4",
            "'abcTES', 3",
            "'TESabc', 0",
            "'TESETES', 3",
            "'TETEST', 4",
            "'TTEST', 4",
            "'TTEESSTT', 1",
            "'TESTA', 4",
            "'ATESTB', 4",
            "'XYZTEST123', 4",
            "'ABCTEST', 4",
            "'TESTXYZ', 4",
            "'TESTTEST', 4",
            "'XXTESTYYTESTZZ', 4"
    })
    void processReturnsExpectedState(String input, int expectedStateCode) {
        TestAutomaton automaton = new TestAutomaton();
        TestAutomaton.State state = automaton.process(input);
        assertEquals(expectedStateCode, state.getCode());
    }

    @ParameterizedTest
    @CsvSource({
            "'', false",
            "'a', false",
            "'TES', false",
            "'TEA', false",
            "'TESA', false",
            "'TESS', false",
            "'TEAST', false",
            "'TXEST', false",
            "'TETS', false",
            "'TTES', false",
            "'ETES', false",
            "'TESETES', false",
            "'TTEESSTT', false",
            "'TEST', true",
            "'abcTESTabc', true",
            "'TETEST', true",
            "'TTEST', true",
            "'TESTA', true",
            "'ATESTB', true",
            "'XYZTEST123', true",
            "'ABCTEST', true",
            "'TESTXYZ', true",
            "'TESTTEST', true",
            "'XXTESTYYTESTZZ', true"
    })
    void hasTestDetectsPresenceCorrectly(String input, boolean expected) {
        TestAutomaton automaton = new TestAutomaton();
        assertEquals(expected, automaton.hasTest(input));
    }
}
