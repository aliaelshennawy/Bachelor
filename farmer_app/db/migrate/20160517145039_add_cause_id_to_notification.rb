class AddCauseIdToNotification < ActiveRecord::Migration
  def change
    add_column :notifications, :cause_id, :integer
  end
end
