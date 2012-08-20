package org.mapa.test;

import org.mapa.R;

import com.Salsalectric.main.MainActivity;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity>{
	
	private Solo solo;
	
	public MainActivityTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		this.solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testCheckActivity()
	{
		solo.assertCurrentActivity("Wrong Activity!", MainActivity.class);
	}
	
	public void testTodoElemenListFragment()
	{
		assertNotNull( "No TodoListFragment in Main Activity!", solo.getCurrentActivity().getFragmentManager().findFragmentById(R.id.list_fragment) );
	}
	
	public void testTodoEditFragment()
	{
		assertNotNull( "No TodoEditFragment in Main Activity!", solo.getCurrentActivity().getFragmentManager().findFragmentById(R.id.edit_fragment_container) );
		assertTrue( "No EmptyFragment" , solo.searchText(getActivity().getString(R.string.EmptyFragmentText)) );
		View v = solo.getView(R.id.EmptyFragmentText);
		assertNotNull("No EmptyFragment", v);
		assertTrue( "No Empty Message" , v.isShown() );
		
	}
}

