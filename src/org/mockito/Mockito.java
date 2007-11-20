package org.mockito;

import java.util.*;

import org.mockito.exceptions.*;
import org.mockito.internal.*;

@SuppressWarnings("unchecked")
public class Mockito extends Matchers {

    public static <T> T mock(Class<T> classToMock) {
        MockFactory<T> proxyFactory = new MockFactory<T>();
        MockControl<T> mockControl = new MockControl<T>(MockitoState.instance(), LastArguments.instance());
        return proxyFactory.createMock(classToMock, new ObjectMethodsFilter<MockControl>(
                classToMock, mockControl));
    }

    public static <T> MockitoExpectation<T> stub(T methodCallToStub) {
//        MockitoState.instance().stubbingStarted();
        
        //TODO increment number of stubs
        MockitoExpectation controlToStub = MockitoState.instance().pullControlToBeStubbed();
        if (controlToStub == null) {
            throw new MissingMethodInvocationException();
        }
        return controlToStub;
    }
    
    public static <T> T verify(T mock) {
        MockUtil.validateMock(mock);
        MockitoState.instance().verifyingStarted(VerifyingMode.anyTimes());
        return mock; 
    }
    
    public static <T> T verify(T mock, int exactNumberOfInvocations) {
        MockUtil.validateMock(mock);
        MockitoState.instance().verifyingStarted(VerifyingMode.times(exactNumberOfInvocations));
        return mock;
    }

	public static void verifyNoMoreInteractions(Object ... mocks) {
	    MockitoState.instance().checkForUnfinishedVerification();
	    for (Object mock : mocks) {
            MockUtil.getControl(mock).verifyNoMoreInteractions();
        }
	}

    public static void verifyZeroInteractions(Object ... mocks) {
        MockitoState.instance().checkForUnfinishedVerification();
        for (Object mock : mocks) {
            MockUtil.getControl(mock).verifyZeroInteractions();
        }
    }
    
    public static <T> VoidMethodExpectation<T> stubVoid(T mock) {
//        MockitoState.instance().reportControlForStubbing(mockControl)
        return MockUtil.getControl(mock);
    }

    //TODO experimental in order syntax (I know is ugly)
    public static <T> T verifyInOrder(T mock, int exactNumberOfInvocations) {
        //TODO validate mocks
        return mock;
    }

    public static <T> T verifyInOrder(T mock) {
        //TODO validate mocks
        return mock;
    }
}