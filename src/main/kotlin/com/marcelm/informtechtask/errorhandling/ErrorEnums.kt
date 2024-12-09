package com.marcelm.informtechtask.errorhandling

class ErrorEnums {
    // Used in a case where we need only to get either Ok or Error,
    // and the type of error doesn't really matter.
    enum class Empty

    enum class PersistenceItemErrors {
        ItemNotFound,
        NameAlreadyExists,
        ItemIdNotProvided,
    }

    enum class RepairErrors {
        RepairAlreadyInProgress,
        FieldsMissing,
        RepairAlreadyFinished,
        RepairNotFound,
        RepairNotInProgress,
    }
}
