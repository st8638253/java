package com.example.automaton;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAutomatonTest {

    @ParameterizedTest
    @CsvSource({
            "'', 0",              
            "'A', 0",             
            "'T', 1",             
            "'TE', 2",            
            "'TES', 3",           
            "'TEST', 4",          
            "'TT', 1",            
            "'TA', 0",            
            "'TET', 1",           
            "'TEA', 0",           
            "'TESA', 0",          
            "'TESTA', 4",         
            "'abcTESTabc', 4",    
            "'abcTES', 3"         
    })
    void processCoversAllTransitions(String input, int expectedStateCode) {
        TestAutomaton automaton = new TestAutomaton();
        TestAutomaton.State state = automaton.process(input);
        assertEquals(expectedStateCode, state.getCode());
    }

    @ParameterizedTest
    @CsvSource({
            "'', false",
            "'A', false",
            "'TES', false",
            "'TET', false",
            "'TEA', false",
            "'TESA', false",
            "'TEST', true",
            "'abcTESTabc', true",
            "'TTTEST', true",
            "'TESTA', true",
            "'TETEST', true"
    })
    void hasTestFunctionalCases(String input, boolean expected) {
        TestAutomaton automaton = new TestAutomaton();
        assertEquals(expected, automaton.hasTest(input));
    }
}
