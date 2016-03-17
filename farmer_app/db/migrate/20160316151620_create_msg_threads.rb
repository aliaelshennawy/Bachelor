class CreateMsgThreads < ActiveRecord::Migration
  def change
    create_table :msg_threads do |t|
      t.boolean :solved

      t.timestamps null: false
    end
  end
end
