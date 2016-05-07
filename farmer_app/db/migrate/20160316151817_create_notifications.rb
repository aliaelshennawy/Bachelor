class CreateNotifications < ActiveRecord::Migration
  def change
    create_table :notifications do |t|
      t.string :text

      t.timestamps null: false
    end
  end
end
