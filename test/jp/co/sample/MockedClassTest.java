package jp.co.sample;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class MockedClassTest {

    @Test
    public void test() throws Exception {

        // ���Ғl
        String expected_methodA = "test";
        String expected_methodB = "val2";

        // �ΏۃN���X�̃��b�N��
        MockedStatic<MockedClass> mocked = Mockito.mockStatic(MockedClass.class);
        // �߂�l��ݒ肵�ăX�^�u��
        mocked.when(MockedClass::methodA).thenReturn(expected_methodA);
        // �X�^�u�����Ȃ��ꍇ
        mocked.when(MockedClass::methodB).thenCallRealMethod();

        // ���s
        String actual_methodA = MockedClass.methodA();
        String actual_methodB = MockedClass.methodB();

        // ���ʊm�F
        assertEquals(expected_methodA, actual_methodA);
        assertEquals(expected_methodB, actual_methodB);
        
        // ���̑�
        // �����_���ɂ�郁�\�b�h�w��̕��@
        mocked.when(() -> MockedClass.methodC(Mockito.anyString()))
        .thenReturn("stub");
        
        mocked.when(() -> MockedClass.methodA())
        .thenReturn("stub");
    }
}
