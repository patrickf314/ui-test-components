// noinspection BadExpressionStatementJS
/**
 * Waits for the given node to not be disabled.
 *
 * @param {HTMLButtonElement} node the node
 * @returns {Promise<void>} the promise resolving when the button is not disabled
 */
node => new Promise((resolve, reject) => {
    const timeout = 10; //second

    let counter = timeout * 20;

    function checkDisabled() {
        counter--;
        if (counter === 0) {
            reject(new Error(`Button is still disabled after ${timeout} seconds.`));
            return;
        }

        if (node.disabled) {
            setTimeout(checkDisabled, 50);
        } else {
            resolve();
        }
    }

    checkDisabled();
})