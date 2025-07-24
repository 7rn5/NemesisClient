package nemesis.setting.impl;

import nemesis.setting.Setting;

public class GroupSetting extends Setting<Boolean> {
    private boolean open = false;

    public GroupSetting(String name) {
        super(name); 
    }

    //public GroupSetting(String name, Visibility visibility) {
    //    super(name);
    //}

    //public void toggle() {
    //    open = !open;
    //}

    @Override
    public Boolean get() {
        return open;
    }

    @Override
    public void set(Boolean value) {
        open = value;
    }

    public static class GroupIs extends Setting.VisibilityCondition {
        private final GroupSetting group;

        public GroupIs(GroupSetting group) {
            //super(() -> true); 
            this.group = group;
        }

        @Override
        public void update() {
            setVisible(group.get()); 
        }
    }
}