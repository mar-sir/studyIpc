#自定义View
##自定义View流程
######通过ViewRoot的performTraversals()调用，经过onMeasure(),onLayout(),onDraw()三大流程，完成对一个View的绘制和显示。
###onMeasure()
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.e(TAG, TAG + "------>onMeasure()");
        }
######从onMeasure()这个方法我们能知道View的测量模式和大小,这要从一个非常重要的类MeasureSpec说起。