LoopViewPager
====================
An android ViewPager that allowing infinite scrolling.

Rewrite the ViewPager code that let it support infinite scrolling.



Usage
-----
To use it simply change `<android.support.v4.view.ViewPager>` to `<com.xs.view.LoopViewPager>`

It support `PagerAdapter` and `FragmentPagerAdapter` you do not need to change any code.

The only thing you need to know that the position is not real position.

For example, is the data size if 4, and the page limit is 1, and the four pa .
    * at first, the index [-1, 0, 1] page will create
	* after fliping left 3 pages, the index [2, 3, 4] page will create.
	* real postion should be mapping `realPosition = (count + position%count) % count`, so index -1 will create the fourth page, and index 4 will create first page.
	

Some Demo Code
--------------
private static final String[] CONTENT = new String[] {"A", "B", "C", "D"};
FragmentPagerAdapter adapter = new TestAdapter(getSupportFragmentManager());

LoopViewPager mLoopViewPager = (LoopViewPager) findViewById(R.id.loop_viewpager);
mLoopViewPager.setAdapter(adapter);
mLoopViewPager.setLoopEnable(true);

class TestAdapter extends FragmentPagerAdapter {

        public TestAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            position = (CONTENT.length + position%CONTENT.length)%CONTENT.length;
            ListViewFragment fragment = new ListViewFragment(CONTENT[position]);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
}



Demo
----
    you can see the demo in src/com/xs/demo/LoopViewPagerDemo


